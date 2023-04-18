<script>
    import {createEventDispatcher, onMount} from 'svelte';

    export let files;

    const dispatch = createEventDispatcher();

    const upFile = (event) => {
        console.log('call upFile');

        console.log(event);
        console.log(files);

        files = event.target.files;

        dispatch('upFile', {files: event.target.files});
    }

    const delFile = () => {
        console.log('call delFile');
        files = {};
    }

    onMount(() => {
        console.log(document.getElementById('upFile').files)
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

    <div class="fileControll">
        <button on:click={delFile}>파일삭제</button>
    </div>
</div>
