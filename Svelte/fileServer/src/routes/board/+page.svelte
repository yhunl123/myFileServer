<script>
    import axios from "axios";
    import {onMount} from "svelte";
    import {navigate} from 'svelte-routing';

    let isLoading = false;
    let boardData = [{
        boardItemId: '',
        boardItemTitle: '',
        boardItemCtDate: '',
    }];

    const getBoardItem = () => {
        isLoading = true;
        axios.get('/api/board/getList')
            .then((res) => {
                if(res.data.success) {
                    boardData = res.data.data.view;
                }
            }).catch((error) => {
                console.log('Error : getBoardItem ' + error)
            }).finally(() => {
                isLoading = false;
            });
    }

    // const goRouter = (id) => {
    //     navigate('/board/detail', )
    // }


    onMount(() => {
        getBoardItem();
    })

</script>


<div style="margin: 10px;">
    <div>
        <table border="1" style="width: 100%">
            <colgroup>
                <col style="width: 10%;" />
                <col style="width: 70%;" />
                <col style="width: auto;" />
            </colgroup>
            <tr>
                <th>목차</th>
                <th>제목</th>
                <th>작성일</th>
            </tr>
            {#each boardData as item}
                <tr>
                    <td>{item.boardItemId}</td>
                    <td><a href="/board/detail?seq={item.boardItemId}">{item.boardItemTitle}</a></td>
                    <td>{item.boardItemCtDate}</td>
                </tr>
            {/each}
        </table>
    </div>
    <!--    <page-link-->
    <!--      :pageInfo="this.pageInfo"-->
    <!--      @changePage="changePage"-->
    <!--    >-->
    <!--    </page-link>-->
    <a type="button" href="/board/write" style="margin-top: 20px;">글쓰기</a>
</div>
