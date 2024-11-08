<template>
    <div class="sidebar">
        <button id="addRecipe" @click="showAddRecipes">Add Recipe</button>
        <button id="showRecipes" @click="showRecipes">Show Recipes</button>
        <button>Show Ingredients</button>
        <button>Test</button>
        <button>Test</button>
        <button>Test</button>
        <button>Test</button>
    </div>
</template>

<script>
export default {
    name: 'Sidebar',
    components: {

    },
    data(){
        return {
            currentComponent: null,
        }
    },
    methods: {
        showAddRecipes() {
            this.$emit('showComponent', 'AddRecipes');
        },
        showRecipes(){
            this.$emit('showComponent', 'ShowRecipes');
        },
    }
}
</script>

<style scoped>
.sidebar {
    background-color: rgb(255, 255, 255);
    height: 600px;
    width: 225px;
    padding-left: 20px;
    padding-right: 20px;
    border-bottom-left-radius: 20px;
    border-bottom-right-radius: 20px;
    border-top-left-radius: 20px;
    border-top-right-radius: 20px;
    box-shadow: 0 4px 4px rgba(77, 78, 78, 0.375);
}

button {
    background-color: rgba(40, 156, 171, 0.626);
    color: white;
    border: none;
    padding: 10px;
    margin-top: 20px;
    cursor: pointer;
    width: 200px;
    border-radius: 5px;
}

button:hover {
    background-color: rgba(40, 156, 171, 0.8);
    box-shadow: 0 2px 3px rgba(66, 66, 66, 0.405);
}

</style>