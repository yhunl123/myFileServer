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

      <b-button type="button" variant="primary" @click="upload" style="margin-top: 10px;">Submit</b-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "UploadCenter",

  data() {
    return {
      title: '',
      contents: '',
      file: null,
    }
  },

  methods: {
    upFile(file) {
      this.file = file;
    },

    delFile() {
      this.file = null;
    },

    upload() {
      console.log(this.file)
    },
  },

  mounted() {

  },

}
</script>

<style scoped>

</style>
