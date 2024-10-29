import './assets/main.css'

import vuetify from './plugins/vuetify'; // Importa Vuetify

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(router)
app.use(vuetify) // Usa Vuetify

app.mount('#app')
