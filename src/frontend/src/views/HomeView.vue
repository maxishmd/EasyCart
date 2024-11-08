<template>
  <Header @showComponent="showComponent"></Header>
  <h1>{{ msg }}</h1>
  <body>
    <div class="home">
      <Sidebar @showComponent="showComponent"></Sidebar>
      <div class="view">
        <component :is="currentComponent" @showComponent="showComponent" ></component>
      </div>

    </div>
  </body>
</template>

<script>
import Header from '@/components/Header.vue';
import Sidebar from '@/components/Sidebar.vue';
import AddRecipes from '@/components/AddRecipes.vue';
import ShowRecipes from '@/components/ShowRecipes.vue';
import HelloView from '@/components/HelloView.vue';

export default {
  name: 'HomeView',
  components: {
    Header, Sidebar, AddRecipes, ShowRecipes, HelloView
  },
  data() {
    return {
      currentComponent: HelloView, //Standardkomponente
      msg: ''
    }
  },
  methods: {
    showComponent(component) {
      this.currentComponent = component;
    }
  }

}

</script>

<style scoped>
.home {
  height: 1000px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}

.view {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
