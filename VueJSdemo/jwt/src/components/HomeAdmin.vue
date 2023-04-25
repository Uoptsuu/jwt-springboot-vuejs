<template>
    <div class="table-responsive" style="width: 70%; margin: auto;">
        <h4 class="display h-5" text-align="center">Danh sách người dùng</h4>
        <a :href="'/admin/insert/'" class="btn btn-primary" style="width: 150px; margin-bottom: 10px;">Add</a>
        <div class="alert alert-success" role="alert" v-bind:style="{ display }">
            {{ message }}
        </div>
        <div v-if="this.Users.length == 0" class="alert alert-secondary" role="alert">
            Danh sách trống
        </div>
        <table class="table table-striped table-hover table-borderless table-primary align-middle">
            <thead class="table-light">
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Address</th>
                    <th>Role</th>
                    <th>Active</th>
                    <th></th>
                </tr>
            </thead>
            <tbody class="table-group-divider">
                <tr class="table-primary" v-for="user in Users" v-bind:key="user.id">
                    <td scope="row" style="font-weight: bold;">{{ user.id }}</td>
                    <td>{{ user.username }}</td>
                    <td>{{ user.address }}</td>
                    <td>{{ user.role }}</td>
                    <td>{{ user.active }}</td>
                    <td>
                        <a :href="'/admin/update/' + user.id">Update</a>
                        |
                        <a href="" @click="deleteUser(user.id)">Delete</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
  
<script>
    import UserService from '../service/UserService.js'

    export default {
        name :'HomeAdmin',
        data(){
            return {
                Users: [],
                display: 'none',
                message: ''
            }
        },
        methods: {
            getUsers(){
                UserService.getUsers().then((res => {
                    console.log(res);
                    this.Users = res.data;
                }))
            },
  
            deleteUser(id){
                if (confirm("Are you sure you want to delete?")) {
                    UserService.delete(id).then(() => {
                        alert("Deleted.");
                        this.getUsers();
                    })
                    .catch((err) => {console.log(err)});
                } else {}
            }
        },
        created() {
            UserService.checkLogin(this.$router);
            //this.$router.go(this.$router.currentRoute)
            //console.log(sessionStorage.getItem("jwtToken"));
            if (sessionStorage.getItem('change') != null && sessionStorage.getItem('change')){
                this.display = 'block';
                this.message = sessionStorage.getItem('msg');
                sessionStorage.removeItem('change');
            } 
            this.getUsers();
        }
    }
</script>

  