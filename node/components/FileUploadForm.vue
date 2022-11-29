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
  },

  data() {
    return {
      file: null,
    }
  },

  methods: {
    upFile() {
      console.log(this.file);
      this.$emit('upFile', this.file);
    },

    delFile() {
      this.file = null;
      this.$emit('delFile');
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
