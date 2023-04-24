<template>
    <form @submit.prevent="update">
        <input type="hidden" name="id" v-model="User.id">
        <label>Username: </label>
        <input type="text" v-model="User.username" name="username">
        <br>
        <br>
        <label>Address: </label>
        <input type="text" v-model="User.address" name="address">
        <br>
        <br>
        <label>Role: </label>
        <div v-for="role in Roles" v-bind:key="role.id">
            <input type="radio" v-model="User.role" name="roleId" value={{role.id}}>
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
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</template>

<script>
    import UserService from '../service/UserService.js'
    export default {
        name :'Update',
        data(){
            return {
                User: {
                    id:'',
                    username: '',
                    address: '',
                    role: '',
                    active: ''
                },
                Roles: []
            }
            
        },
        methods: {
            setData(){
                UserService.getDataForUpdate(this.$route.params.id).then((res => {
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
            //UserService.checkLogin;
            this.setData();
        }    
    }
</script>
  