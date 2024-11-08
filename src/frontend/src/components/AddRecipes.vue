<template>
    <div class="add-recipes">
        <h1>Add Recipe</h1>
        <div class="EditorContainer">
            <form>
                <p>Enter the Name and a Description of your new recipe.</p>
                <div class="InfoContainer">
                    <input type="text" maxlength="50" placeholder="Recipename" id="name" v-model="name" required>
                    <input type="text" maxlength="100" placeholder="Description" id="description" v-model="description">
                </div>
            </form>
            <div class="InstructionsContainer">
                <p>Give us your Instructions for your Meal.</p>
                <textarea class="valid" cols="70" rows="20" placeholder="Instructions" v-model="instructions" required></textarea>
            </div>
            <IngredientInput @getInput="getInput"></IngredientInput>
        </div>
        <div class="SaveForm">
            <button id="save" @click="save">Save</button>
            <button id="cancel" @click="cancel">Cancel</button>
        </div>
    </div>
</template>

<script>
import IngredientInput from '@/components/IngredientInput.vue'
import IngredientList from '@/components/IngredientList.vue';
export default {
    name: 'AddRecipes',
    components: {
        IngredientList,
        IngredientInput
    },
    data() {
        return {
            newRecipeId: null,
            ingredientId: null,
            name: '',
            description: '',
            instructions: '',
            ingredient: '',
            amount: '',
            vegetarian: false,
            vegan: false,
            glutenfree: false,
            ingredients: []
        }
    },
    methods: {
        async save() {
            const url = 'api/recipes';
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: this.name,
                    description: this.description,
                    instructions: this.instructions,
                    ingredients: null // Setze dies auf die korrekten Daten falls vorhanden
                })
            });
            if (!response.ok) {
                throw new Error('Fehler beim Hinzufügen des Rezepts: ' + response.statusText);
            }
            const contentType = response.headers.get("content-type");
            if (contentType && contentType.includes("application/json")) {
                const data = await response.json();
                console.log('Antwort vom Server:', data);
                if (data && data.id) {
                    this.newRecipeId = data.id;
                    console.log('Rezept erfolgreich hinzugefügt, ID:', this.newRecipeId);
                } else {
                    console.warn('ID nicht in der Antwort gefunden');
                }
            } else {
                throw new Error('Die Antwort ist kein gültiges JSON');
            }
            await this.addIngredients();

            this.$emit('showComponent', 'HelloView');
        },
        cancel() {
            this.$emit('showComponent', 'HelloView');
        },
        getInput(ingredients) {
            this.ingredients = ingredients;
            //TODO weiter mit methode das bei safe alles in db gespeichert wird
        },
        async addIngredients() {
            const url = 'api/ingredients';
            for (const ingredient of this.ingredients) {
                const response = await fetch(url, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        name: ingredient.ingredient,
                        amount: ingredient.amount,
                        vegetarian: ingredient.vegetarian,
                        vegan: ingredient.vegan,
                        glutenfree: ingredient.glutenfree,
                        recipe: {
                            id: this.newRecipeId
                        }
                    })
                });
                if (!response.ok) {
                    throw new Error('Fehler beim Hinzufügen der Zutaten: ' + response.statusText);
                }
            }

        }
    }
}
</script>

<style scoped>
.add-recipes {
    font-family: Arial, sans-serif;
    height: 150%;
    background-color: rgba(0, 0, 0, 0.19);
    padding: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
    border-radius: 8px;
    margin: 10px;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
}

.EditorContainer {
    padding: 20px;
    margin: 20px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: normal;

}

.InfoContainer {
    display: flex;
    gap: 20px;
}

.InfoContainer input {
    flex: 1;
}

.InstructionsContainer {
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.IngredientsContainer {
    padding: 20px;
}

.IngredientInputContainer {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
    align-items: center;
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

.itemsContainer {
    display: flex;
    flex-direction: row;
}

.extra {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 20px;
}

input,
textarea {
    background-color: rgb(255, 255, 255);
    border: none;
    border-radius: 5px;
    font-size: 16px;
    text-align: left;
    box-sizing: content-box;
    padding-left: 5px;
    margin: 5px;
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;

}

.SaveForm {
    display: flex;
    flex-direction: row;
    justify-content: center;
    padding: 10px;
    margin: 25px;
    border-radius: 15px;
    background-color: rgba(213, 213, 213, 0.633);
    box-shadow: 0 4px 8px rgba(7, 80, 144, 0.654);
    width: 300px;
}

button {
    color: white;
    padding: 10px;
    margin-left: 50px;
    margin-right: 50px;
    border-radius: 5px;
    border: none;
    cursor: pointer;
}

#save {
    background-color: rgb(42, 131, 53);
}

#cancel {
    background-color: rgb(131, 42, 42);
}
</style>
