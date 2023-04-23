<!-- eslint-disable vue/multi-word-component-names -->
<!-- eslint-disable vue/multi-word-component-names -->
<template>
    <form @submit="login" method="post">
        <label>Username: </label>
        <input type="text" v-model="User.username" name="username">
        <br>
        <label>Password: </label>
        <input type="password" v-model="User.password" name="password">
        <div class="box-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
            <a href="/" class="btn btn-info">Cancel</a>
        </div>
    </form>
</template>

<script>
    import axios from 'axios';
  
    export default {
        // eslint-disable-next-line vue/multi-word-component-names
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
                axios.post('http://localhost:8080/api/auth/login',this.User) 
                // .then(() => {
                //   alert("saveddd");
                //   // eslint-disable-next-line no-undef
                //   redirect('/home');
                // })
                .catch((err) => {console.log(err)})
            }
        },
        created() {
        }    
    }
</script>
  