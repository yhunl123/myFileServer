<template>
  <div id="upWrapper" style="margin: 10px; text-align: -webkit-center;">
    <div id="uploadForm" style="text-align: left;">
      <b-form-input
          id="input-1"
          v-model="title"
          type="text"
          placeholder="제목 입력"
          required
          style="margin-bottom: 10px;"
      ></b-form-input>

      <b-form-textarea
        id="textarea"
        v-model="contents"
        placeholder="내용 입력"
        rows="6"
        max-rows="10"
        style="margin-bottom: 10px;"
      ></b-form-textarea>

      <FileUploadForm
        @upFile="upFile"
        @delFile="delFile"
      ></FileUploadForm>

      <b-button
        type="button"
        @click="upload"
        style="margin-top: 10px; background-color: #ff9c00"
      >Submit</b-button>
    </div>
  </div>
</template>

<script>
import fileService from "~/service/FileService";

export default {
  name: "UploadCenter",

  data() {
    return {
      title: '',
      contents: '',
    }
  },

  methods: {
    upload() {
      const params = new FormData();
      params.append('boardItemTitle', this.title);
      params.append('boardItemContents', this.contents);
      params.append('file', this.file);

      this.$axios
        .post('/api/board/upload', params, {
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
            console.log('test');
          }
        }).catch((err) => {
          console.log('File upload fail!')
          console.log('detail :>>>> ', err)
       })
    },
  },

  mounted() {

  },

}
</script>

<style scoped>

</style>
