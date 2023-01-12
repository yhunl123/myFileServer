<template>
  <div style="margin: 10px;">
    <div>
      <b-table
        :items="items"
        :fields="fields"
        :sort-by.sync="sortBy"
        :sort-desc.sync="sortDesc"
        :bordered="true"
        :hover="true"
        head-variant="light"
      >
        <template>

        </template>
      </b-table>
    </div>
<!--    <page-link-->
<!--      :pageInfo="this.pageInfo"-->
<!--      @changePage="changePage"-->
<!--    >-->
<!--    </page-link>-->
    <b-button type="button" variant="primary" @click="write" style="margin-top: 20px;">글쓰기</b-button>
  </div>
</template>

<script>
export default {
  name: "Board",

  data() {
    return {
      sortBy: 'first_name',
      sortDesc: false,
      fields: [
        { key: 'boardItemId', sortable: true, label: '순번' },
        { key: 'boardItemTitle', sortable: true, label: '제목' },
        { key: 'boardItemCtDate', sortable: true, label: '작성일' }
      ],
      items: [
        {
          boardItemId: '',
          boardItemTitle: '',
          boardItemCtDate: '',
        },
      ],

    }
  },

  methods: {
    write() {
      this.$router.push('/uploadCenter')
    },

    getBoardItem() {
      this.$axios
        .get('/api/board/getList')
        .then((res) => {
          if (res.data.success) {
            this.items = res.data.data.view
            console.log(this.items)
          }
        })
        .catch((err) => {
          console.log('GetBoard Error :>>>> ', err)
        })
    },


  },

  mounted() {
    this.getBoardItem();


  },

  created() {

  },
}
</script>

<style scoped>

</style>
