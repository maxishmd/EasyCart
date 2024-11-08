
<template>
  <router-view/>
</template>

<script>
import HomeView from './views/HomeView.vue';
  export default {
    name: 'App',
    components: {
    HomeView
}
  }
</script>

<style>
body {
  margin: 0;
}

input {
  border: 0;
  border-radius: 8px;
  width: 100%;
  height: 30px;
}

</style>