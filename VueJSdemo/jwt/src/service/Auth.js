import axios from 'axios'
axios.defaults.headers.get['Accepts'] = 'application/json';
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
axios.defaults.headers.common['Access-Control-Allow-Headers'] = 'Origin, X-Requested-With, Content-Type, Accept';
axios.defaults.headers.common['Access-Control-Allow-Method'] = ['GET, POST, PUT, DELETE'];

axios.defaults.baseURL = 'http://localhost:8080/api/auth'
axios.defaults.headers.common['Authorization'] = 'Bearer ' + sessionStorage.getItem("jwtToken");
