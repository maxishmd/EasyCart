<template>
    <header>
        <p>Add your ingredients</p>
    </header>

    <body>
        <form>
            <input type="text" placeholder="Ingredient" v-model="ingredient">
            <input type="text" placeholder="Amount" v-model="amount">
            <div class="itemsContainer">
                <div class="item">
                    <input type="checkbox" value="true" id="vegetarian" v-model="vegetarian">
                    <p>Vegetarian</p>
                </div>
                <div class="item">
                    <input type="checkbox" value="true" id="vegan" v-model="vegan">
                    <p>Vegan</p>
                </div>
                <div class="item">
                    <input type="checkbox" value="true" id="glutenfree" v-model="glutenfree">
                    <p>Glutenfree</p>
                </div>
                <button class="addIngredient" value="true" type="button" @click="setIngredients">Add</button>
            </div>
        </form>
        <div class="IngredientListContainer">
            <IngredientList :ingredients="ingredients"/>
        </div>
    </body>
</template>

<script>
import IngredientList from '@/components/IngredientList.vue';
export default {
    name: 'IngredientInput',
    components: {
        IngredientList
    },
    data() {
        return {
            ingredient: '',
            amount: '',
            vegetarian: false,
            vegan: false,
            glutenfree: false,
            ingredients: []
        }
    },
    methods: {
        giveInput(){
            this.$emit('getInput', this.ingredients)
        },
        async setIngredients() {
            if (this.ingredient && this.amount) {
                this.ingredients.push({
                    ingredient: this.ingredient,
                    amount: this.amount,
                    vegetarian: this.vegetarian,
                    vegan: this.vegan,
                    glutenfree: this.glutenfree
                });
                this.ingredient = '';
                this.amount = '';
                this.vegetarian = false;
                this.vegan = false;
                this.glutenfree = false;
            }
            this.giveInput();
        }
    }

}
</script>

<style scoped>
header {
    padding: 20px;
}

body {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
    align-items: center;
}

.itemsContainer {
    display: flex;
    flex-direction: row;
}

.item {
    display: flex;
    align-items: center;
    flex-direction: row;
    padding-right: 50px;
    padding-left: 0px;
    margin-left: 0px;
    gap: 0px;
}

.item input {
    width: 30px;
    height: 20px;
}

.addIngredient {
    gap: 10px;
    margin: 50px;
}
</style>