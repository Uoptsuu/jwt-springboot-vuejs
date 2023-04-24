<template>
    <div v-for="user in Users" v-bind:key="user.id">
        <label for="username">Username: </label>
        <p id="username" name="username">{{ user.id }}</p>
        <br>
        <br>
        <label for="role">Role: </label>
        <p id="role" name="role">{{ user.roleId }}</p>
        <br>
        <br>
        <label for="address">Address: </label>
        <p id="address" name="address">{{ user.address }}</p>
        <br>
        <br>
        <label for="active">Active: </label>
        <p id="active" name="active">{{ user.active }}</p>
        <br>
        <br>
        <a :href="'/auth/admin/update/' + user.id"><button>Update</button></a>
        <a href="" @click="delete(user.id)"><button>Delete</button></a>
        <hr>
    </div>
</template>
  
<script>
    import UserService from '../service/UserService.js'
    export default {
        name:'HomeAdmin',
        data(){
            return {
                Users: []
            }
        },
        methods: {
            getUsers(){
                UserService.getUsers().then((res => {
                    console.log(res);
                    this.Users = res.data;
                }))
            },
  
            delete(id){
                console.log(id)
                UserService.delete(id).then(() => {
                  alert("Deleted.");
                  this.getUsers();
                })
                .catch((err) => {console.log(err)});
            }
        },
        created() {
            this.getUsers();
        }    
    }
</script>

  