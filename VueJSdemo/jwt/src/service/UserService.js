import axios from 'axios'

class UserService{
    login(loginRequest) {
        var login = axios.create();
        delete login.defaults.headers.common['Authorization']
        return login.post(`/login`, loginRequest)
    }
    homePageUser() {
        return axios.get(`/user/home`)
    }
    search(key) {
        return axios.get(`/admin/get-users?key=${key}`)
    }
    getUsers(){
        return axios.get(`/admin/get-users`)
    }
    getUser(userId) {
        return axios.get(`/admin/get-user/${userId}`)
    }
    getRoles() {
        return axios.get(`/admin/get-roles`)
    }
    insert(insertRequest) {
        return axios.post(`/admin/insert-user`, insertRequest)
    }
    update(updateRequest) {
        return axios.put(`/admin/update-user`, updateRequest)
    }
    delete(userId) {
        return axios.delete(`/admin/delete-user/${userId}`)
    }
    checkSpecialChar(input){
        const format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
        return format.test(input);
    }
    checkLogin(router) {
        if(window.sessionStorage.getItem("jwtToken") == null || window.sessionStorage.getItem("jwtToken") == "") {
            router.push("/");
        }
    }
}

export default new UserService()