<script>
    import {createEventDispatcher, onMount} from 'svelte';
    import instance from "../../../axios/axios";

    const dispatch = createEventDispatcher();
    export let files;
    let delBtn = false;
    let progress = 0;

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
            }).then((res) => {
            const data = res.data;
            if (data.success) {
                dispatch('upFile', {files: data.data.view});
                delBtn = true;
            }
        }).catch((err) => {
            console.log('File upload fail!')
            console.log('detail :>>>> ', err)
        })
    }

    const delFile = () => {
        console.log('call delFile');
        files = {};
        delBtn = false;
        dispatch('upFile', {files: {}});
    }

    onMount(() => {
    })
</script>

<div>
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
