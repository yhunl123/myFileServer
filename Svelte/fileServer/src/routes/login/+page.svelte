<script>
    import instance from "../../axios/axios.js";


    let loginForm = {
        memberName: '',
        memberPw: '',
    };
    let isLoading = false;

    const loginProc = () => {
        if (loginForm.memberName === '') {
            return false;
        }
        if (loginForm.memberPw === '') {
            return false;
        }

        const formData = new FormData();

        formData.append("memberName", loginForm.memberName)
        formData.append("memberPw", loginForm.memberPw)

        instance.post('/api/login/login', formData)
            .then((res) => {
                const data = res.data.data.view;
                if (res.data.success) {
                    localStorage.setItem('userToken', data.userToken);

                    window.location.href = '/'
                }
            })
            .catch((err) => {
                console.log('login error :>>>> ', err);
            })
            .finally(() => {
                isLoading = false;
            })
    }

</script>

<div>
    <div class="loginDiv">
        <form class="loginForm">
            <input
                    id="input-1"
                    placeholder="Enter name"
                    required
                    bind:value="{loginForm.memberName}"
            >
            <input
                    id="input-2"
                    placeholder="Enter password"
                    type="password"
                    required
                    bind:value="{loginForm.memberPw}"
            >
            <button
                    on:click="{loginProc}"
                    style="width: 100%; background-color: #ff9c00"
            >
                <span>로그인</span>
                <!--                <spinner small v-if="isLoading"></spinner>-->
            </button>
        </form>
    </div>
</div>
