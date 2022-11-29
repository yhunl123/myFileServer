<template>
  <div style="margin: 10px;">
    <div>
      <table>
        <colgroup>
          <col style="width: 15%;" />
          <col style="width: auto;" />
          <col style="width: 20%;" />
        </colgroup>
        <thead>
          <tr>
            <th id="boardId" scope="col">순번</th>
            <th id="boardTitle" scope="col">글제목</th>
            <th id="boardCtDate" scope="col">작성일</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, idx) in this.data" :key="idx">
            <td>{{ item.boardItemId }}</td>
            <td>
              <router-link
                :to="{ name: 'contents', query: { id: item.boardItemId }}"
                :value="item.boardItemId"
              >
                {{ item.boardItemTitle }}
              </router-link>
            </td>
            <td>{{ item.boardItemCtDate }}</td>
          </tr>
        </tbody>
      </table>
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
      data: [{
        boardItemId: '1',
        boardItemTitle: '2',
        boardItemCtDate: '3',
      }],

    }
  },

  methods: {
    write() {
      console.log('test')
    },
  },

  mounted() {

  },

  created() {

  },
}
</script>

<style scoped>

</style>
