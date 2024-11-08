import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

createApp(App).use(router).mount('#app')

//fetch('http://localhost:3001/todos).then(response => response.json()).then(data => console.log(data)).catch(error => console.log(error))
