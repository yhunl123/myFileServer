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
        :fileInfo="file"
        @fileUpload="fileUpload"
        @deleteFile="deleteFile"
      ></FileUploadForm>

      <b-button
        type="button"
        @click="upload"
        style="margin-top: 10px; background-color: #ff9c00"
      >작성</b-button>
    </div>
    <b-modal
      id="upFormModal"
      title="BootstrapVue"
      centered
      hide-footer
      hide-header
      no-close-on-esc
      no-close-on-backdrop
    >
      <div class="d-block text-center">
        <h3>{{modalText}}</h3>
      </div>
      <b-button
        class="mt-3"
        block
        @click="$bvModal.hide('upFormModal')"
        style="background-color: #ff9c00"
      >확인
      </b-button>
    </b-modal>
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
      file: {
        fileName: '',
        fileSize: ''
      },
      modalText: '',
    }
  },

  methods: {
    upload() {
      if(!this.title) {
        this.modalText = '제목을 입력해 주세요.'
        this.$bvModal.show('upFormModal')

        return;
      }

      const params = new FormData();
      params.append('boardItemTitle', this.title);
      params.append('boardItemContents', this.contents);
      params.append('fileName', this.file.fileName);

      this.$axios
        .post('/api/board/upload', params)
        .then((res) => {
          const data = res.data;
          if (data.success) {
            this.$router.push('/board')
          }
        }).catch((err) => {
          console.log('Upload Fail :>>>> ', err)
       })
    },

    fileUpload(data) {
      this.file = data
    },
    deleteFile() {
      this.file = {
        fileName: '',
        fileSize: ''
      }
    }
  },

  mounted() {

  },

}
</script>

<style scoped>

</style>
