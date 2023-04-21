<script>
    import {onMount} from "svelte";
    import instance from "../../axios/axios";

    let isLoading = false;
    let boardData = [{
        boardItemId: '',
        boardItemTitle: '',
        boardItemCtDate: '',
        fdThumbPath: '',
        fdThumbName: '',
    }];

    const getBoardItem = () => {
        isLoading = true;
        instance.get('/api/board/getList')
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
    <div class="post-list">
        {#each boardData as item}
            <div class="post-card">
                <a class="post-card-item" href="/board/detail?id={item.boardItemId}">
                    <img src={"@/" + item.fdThumbName} alt="thumbnail" class="post-thumbnail">
                    <div class="post-info">
                        <h3 class="post-title">{item.boardItemTitle}</h3>
                        <p class="post-author">작성자</p>
                        <p class="post-date">{item.boardItemCtDate}</p>
                    </div>
                </a>
            </div>
        {/each}
    </div>
</div>



<style>
    .post-list {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
        gap: 20px;
    }

    .post-card {
        display: flex;
        flex-direction: column;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
    }

    .post-thumbnail {
        width: 100%;
        height: 150px;
        object-fit: cover;
        border-top-left-radius: 5px;
        border-top-right-radius: 5px;
    }

    .post-info {
        padding: 10px;
    }

    .post-title {
        font-size: 1.2rem;
        margin: 0;
        margin-bottom: 10px;
    }

    .post-author,
    .post-date {
        font-size: 0.8rem;
        margin: 0;
        color: #666;
    }

    .post-card-item {
        text-decoration: none;
    }
    .post-card-item:visited {
        color: gray;
    }
    .post-card-item:hover {
        text-decoration: none;
    }
</style>
