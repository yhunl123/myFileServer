<script>
    import instance from "../../../axios/axios";
    import {navigate} from "svelte-routing";

    let files = {};
    let file = {};
    let delBtn = false;
    let progress = 0;

    const upload = () => {
        const params = new FormData();
        params.append('boardItemTitle', file.fileOrigName);
        params.append('boardItemContents', '');
        params.append('fileName', file.fileName);

        instance
            .post('/api/board/upload', params)
            .then((res) => {
                const data = res.data;
                if (data.success) {
                    navigate('/board');
                }
            }).catch((err) => {
            console.log('Upload Fail :>>>> ', err)
        })
    };


    const upFile = (event) => {
        const params = new FormData();
        params.append('file', event.target.files[0]);

        instance
            .post('/api/file/fileUpload', params, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
                onUploadProgress: (progressEvent) => {
                    let progressPer = (progressEvent.loaded * 100) / progressEvent.total;
                    progress = Math.round(progressPer)
                },
            })
            .then((res) => {
                const data = res.data;
                if (data.success) {
                    file = data.data.view;
                    delBtn = true;
                }
            })
            .catch((err) => {
                console.log('File upload fail!')
                console.log('detail :>>>> ', err)
            })
    }

    const delFile = () => {
        files = {};
        delBtn = false;

        const params = new FormData();
        params.append('fileName', file.fileName)

        instance
            .post('/api/file/deleteFile', params)
            .then((res) => {
                if(res.data.success) {
                    file = {};
                }
            })
            .catch((error) => {
                console.log(error)
            })
    }
</script>


<div id="upWrapper" style="margin: 10px; text-align: -webkit-center;">
    <div id="uploadForm" style="text-align: left;">
        <input
                type="file"
                id="upFile"
                placeholder="Choose a file or drop it here..."
                style="margin-bottom: 10px;"
                accept="video/*"
                on:change={upFile}
        />
        {#if progress !== 0}
            <div class="progress">
                <div class="progress-bar" role="progressbar" style="width: {progress}%;" aria-valuenow={progress} aria-valuemin="0" aria-valuemax="100"></div>
            </div>
        {/if}
        {#if delBtn}
            <div class="fileControll">
                <button on:click={delFile}>파일삭제</button>
            </div>
        {/if}

        <button
                type="button"
                on:click="{upload}"
                style="margin-top: 10px; background-color: #ff9c00"
        >작성
        </button>
    </div>
</div>


<style>
    .progress {
        height: 20px;
        margin-bottom: 15px;
        overflow: hidden;
        background-color: #f5f5f5;
        border-radius: 4px;
        -webkit-box-shadow: inset 0 1px 2px rgba(0,0,0,.1);
        box-shadow: inset 0 1px 2px rgba(0,0,0,.1);
    }

    .progress-bar {
        float: left;
        width: 0;
        height: 100%;
        font-size: 12px;
        line-height: 20px;
        color: #fff;
        text-align: center;
        background-color: #337ab7;
        -webkit-box-shadow: inset 0 -1px 0 rgba(0,0,0,.15);
        box-shadow: inset 0 -1px 0 rgba(0,0,0,.15);
        -webkit-transition: width .6s ease;
        -o-transition: width .6s ease;
        transition: width .6s ease;
    }
</style>
