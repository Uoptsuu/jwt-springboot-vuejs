<template>
    <form @submit="update" method="post">
        <input type="hidden" name="id" v-model="User.id">
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
        <label>Active: </label>
        <input type="radio" v-model="User.active" name="active" value="1">
        <label>Yes</label>
        <input type="radio" v-model="User.active" name="active" value="0">
        <label>No</label>
        <br>
        <br>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</template>

<script>
    import UserService from '../service/UserService.js'
    export default {
        name:'Update',
        data(){
            return {
                User: {
                    id:'',
                    username: '',
                    password: '',
                    address: '',
                    roleId: '',
                    active: ''
                },
                Roles: []
            }
            
        },
        methods: {
            setData(){
                UserService.getRoleForInsert().then((res => {
                    console.log(res);
                    this.Roles = res.data.listRole;
                    this.User = res.data.user;
                }))
            },
            update(){
                console.log(this.User)
                UserService.update(this.User) 
                // .then(() => {
                //   // eslint-disable-next-line no-undef
                //   redirect('/');
                // })
                .catch((err) => {console.log(err)})
            }
        },
        created() {
            this.setData();
        }    
    }
</script>
  