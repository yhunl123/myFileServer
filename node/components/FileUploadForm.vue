<template>
  <div>
    <b-form-file
      id="upFile"
      v-model="file"
      placeholder="Choose a file or drop it here..."
      drop-placeholder="Drop file here..."
      style="margin-bottom: 10px;"
      accept="video/*"
      @input="upFile"
    ></b-form-file>

    <div class="fileControll">
      <b-progress
        :value="this.$store.getters.getProgress"
        :max="100"
        show-progress
        animated
        v-if="Boolean(file) && this.$store.getters.getProgress != null && this.$store.getters.getProgress !== 100"
      ></b-progress>
<!--      <video-->
<!--        class="inputVideo"-->
<!--        v-if="Boolean(file)"-->
<!--        src=""-->
<!--      />-->
      <b-button
        @click="delFile"
        variant="danger"
        v-if="Boolean(file)"
      >파일삭제</b-button>
    </div>
  </div>
</template>

<script>

export default {
  name: "FileUploadForm",
  props: {
    fileInfo: {
      fileName: {
        type: String,
      },
      fileSize: {
        type: String,
      },
    }
  },

  data() {
    return {
      file: null,
    }
  },

  methods: {
    upFile() {
      if(!this.file){
        return;
      }

      const params = new FormData();
      params.append('file', this.file);

      this.$axios
        .post('/api/file/fileUpload', params, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
          onUploadProgress: (progressEvent) => {
            let progressPer = (progressEvent.loaded * 100) / progressEvent.total;
            let progressSuccess = Math.round(progressPer);
            this.$store.dispatch('setProgress', progressSuccess);
          },
        }).then((res) => {
        const data = res.data;
        if (data.success) {
          this.$emit('fileUpload', data.data.view);
        }
      }).catch((err) => {
        console.log('File upload fail!')
        console.log('detail :>>>> ', err)
      })
    },

    delFile() {
      const params = new FormData();
      params.append('fileName', this.fileInfo.fileName);

      this.$axios
        .post('/api/file/deleteFile', params)
        .then((res) => {
          this.$emit('deleteFile');
        })
        .catch((err) => {
          console.log('uploadCancle fail :>>>> ', err)
        })

      this.file = null;
    },
  }
}
</script>

<style scoped>
.fileControll {
  text-align: left;
}
.inputVideo {

}
</style>
