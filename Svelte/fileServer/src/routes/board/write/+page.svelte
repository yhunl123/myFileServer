<script>
    import FileUploadForm from "./fileUploadForm.svelte";

    let title = '';
    let contents = '';
    let files = {};

    const upFile = (event) => {
        console.log(event)
        console.log(files)
    }

    const upload = () => {
        console.log(title, contents, files)
    };
</script>


<div id="upWrapper" style="margin: 10px; text-align: -webkit-center;">
    <div id="uploadForm" style="text-align: left;">
        <input
                id="input-1"
                bind:value="{title}"
                type="text"
                placeholder="제목 입력"
                required
                style="margin-bottom: 10px;"
        />

        <textarea
                id="textarea"
                bind:value="{contents}"
                placeholder="내용 입력"
                rows="10"
                style="margin-bottom: 10px;"
        ></textarea>

        <FileUploadForm
                files={files}
                on:upfile={upFile}
        ></FileUploadForm>

        <button
                type="button"
                on:click="{upload}"
                style="margin-top: 10px; background-color: #ff9c00"
        >작성
        </button>
    </div>
</div>
