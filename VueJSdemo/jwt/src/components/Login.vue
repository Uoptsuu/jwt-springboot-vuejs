<template>
    <form @submit="login" method="post">
        <label>Username: </label>
        <input type="text" v-model="User.username" name="username">
        <br>
        <br>
        <label>Password: </label>
        <input type="password" v-model="User.password" name="password">
        <br>
        <br>
        <button type="submit">Submit</button>
    </form>
</template>

<script>
    import UserService from '../service/UserService'
    export default {
        name:'Login',
        data(){
            return {
                User: {
                    username: '',
                    password: ''
                }
            }
        },
        methods: {
            login(){
                console.log(this.User)
                UserService.login(this.User) 
                // .then(() => {
                //   // eslint-disable-next-line no-undef
                //   redirect('/');
                // })
                .catch((err) => {console.log(err)})
            }
        },
        created() {
        }    
    }
</script>
  