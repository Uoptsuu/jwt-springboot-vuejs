<template>
    <form @submit="insert" method="post">
        <label>Username: </label>
        <input type="text" v-model="User.username" name="username">
        <br>
        <br>
        <label>Password: </label>
        <input type="password" v-model="User.password" name="password">
        <br>
        <br>
        <label>Address: </label>
        <input type="password" v-model="User.address" name="address">
        <br>
        <br>
        <label>Role: </label>
        <div v-for="role in Roles" v-bind:key="rode.id">
            <input type="radio" v-model="User.roleId" name="roleId" value="{{role.id}}">
            <label>{{role.name}}</label>
        </div>
        <br>
        <br>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</template>

<script>
    import UserService from '../service/UserService.js'
    export default {
        data(){
            return {
                User: {
                    username: '',
                    password: '',
                    address: '',
                    roleId: ''
                },
                Roles: []
            }
            
        },
        methods: {
            setRole(){
                UserService.getRoleForInsert().then((res => {
                    console.log(res)
                    this.Roles = res.data;
                }))
            },
            insert(){
                console.log(this.User)
                UserService.insert(this.User) 
                // .then(() => {
                //   // eslint-disable-next-line no-undef
                //   redirect('/');
                // })
                .catch((err) => {console.log(err)})
            }
        },
        created() {
            //UserService.checkLogin;
            this.setRole;
        }    
    }
</script>
  