import axios from 'axios'

class UserService{
    login(loginRequest) {
        return axios.post(`/login`, loginRequest)
    }
    homePageUser() {
        return axios.get(`/user/home`)
    }
    getUsers(){
        return axios.get(`/admin/home`)
    }
    getDataForUpdate(user_id) {
        return axios.get(`/admin/update/${user_id}`)
    }
    getRoleForInsert() {
        return axios.get(`/admin/insert`)
    }
    update(updateRequest) {
        return axios.put(`/admin/update`, updateRequest)
    }
    delete(user_id) {
        return axios.delete(`/admin/delete/${user_id}`)
    }
    // checkLogin() {
    //     // if(window.localStorage.getItem("jwtToken") == null || window.localStorage.getItem("jwtToken") != "") {
    //     //     this.$router.push("/");
    //     // }
    // }
}

export default new UserService()