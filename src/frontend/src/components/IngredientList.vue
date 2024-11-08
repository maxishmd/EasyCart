<template>
    <div class="IngredientList">
        <p>Ingredients</p>
        <ul>
            <li v-for="(ingredient, index) in ingredients" :key="index">
                <div class="DataContainer">
                    <p> {{  ingredient.ingredient }}</p>
                    <p> {{  ingredient.amount }}</p>
                    <p> Vegetarian: {{ ingredient.vegetarian }}</p>
                    <p> Vegan: {{ ingredient.vegan }}</p>
                    <p> Glutenfree: {{ ingredient.glutenfree }}</p>
                </div>
            </li>
            <!-- <li>
                <div class="DataContainer">
                    <p>   ingredients.ingredient</p>
                    <p>   ingredients.amount </p>
                    <p>  ingredients.vegetarian </p>
                    <p>  ingredients.vegan</p>
                    <p>  ingredients.glutenfree </p>
                </div>
            </li> -->
        </ul>
    </div>
</template>

<script>
export default {
    name: 'IngredientList',
    props:{
        ingredients: {
            type: Array,
            required: true
        }
    }
}
</script>

<style scoped>
.DataContainer {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    background-color: rgba(213, 213, 213, 0.633);
    padding: 10px;
    margin: 25px;
    border-radius: 15px;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
}
ul {
    list-style-type: none;
    padding: 0;

}
</style>