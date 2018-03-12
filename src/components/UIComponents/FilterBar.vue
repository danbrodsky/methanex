<template>
    <div class="card" style="background-color: #CACACA">
        <div class="filter-option" style="padding-top: 1em">Sort: 
            <select v-model="sortBy" required>
                <option v-for="option in sortingOptions" v-bind:key="option.value" v-bind:value="option.value">{{ option.text }}</option>
            </select>
        </div>
        <div v-for="filter in filters" v-bind:key="filter.id" class="filter-option">
            In 
            <select v-model="filter.filterBy" v-bind:class="{'invalid-category': filter.invalidCategory}" required>
                <!-- <option selected>Select category</option> -->
                <option v-for="option in filterOptions" v-bind:key="option.value.category" v-bind:value="option.value">{{ option.text }}</option>
            </select>
            , search:
            <input style="min-width: 200px" placeholder="Enter search keyword" v-model="filter.keyWord" v-bind:class="{'invalid-keyword': filter.invalidKeyword}" required />
            <span v-on:click="removeFilter(filter.id)"><i class="fa fa-close"></i></span>
        </div>
        <div v-on:click="addFilter" class="filter-option"><i class="fa fa-plus"></i> Add Filter</div>
        <div class="filter-option"><input type="submit" class="btn" style="max-width: 150px;border-color:black;color:black;background-color:white" v-on:click="submitFilters" value="Apply" /></div>
    </div>
</template>

<script>
/**
 * 
 * Generic, reusable filter bar. Requires 3 properties:
 * 
 * @prop value: function to be returned to parent component to filter their list
 * @prop sortingOptions: array of objects in the form: {value: 'object key', text: 'option text'}
 * @prop filterOptions: array of objects in the form: {filterBy: {category: 'object key', type: KeyType}, text: 'option text'}
 * 
 */
export default {
    props: {
        value: Function,
        sortingOptions: Array,
        filterOptions: Array
    },
    mounted: function(){
        this.sortBy = this.sortingOptions[0].value;
    },
    data () {
        return {
            // sortingOptions: this.sortingOptions,
            // filterOptions: this.filterOptions,
            idCounter: 0,
            sortBy: '',
            filters: []
        }
    },
    methods: {
        addFilter () {
            var id = this.idCounter;
            this.filters.push({id: id, filterBy: {category: '', type: null}, keyWord: '', invalidCategory: false, invalidKeyword: false});
            this.idCounter++;
        },
        removeFilter (id) {
            var index = -1;
            for(var key in this.filters) {
                if (this.filters[key].id == id) {
                    index = key;
                }
            }
            if(index > -1){
                this.filters.splice(index, 1);
            }
        },
        submitFilters () {
            if(this.invalidKeywords()) this.sendFilters();
            else console.log("invalid");
        },
        invalidKeywords () {
            var valid = true;
            for(var i=0; i<this.filters.length; i++){
                this.filters[i].invalidCategory=false;
                this.filters[i].invalidKeyword=false;
                if(this.filters[i].filterBy.category==''){
                    this.filters[i].invalidCategory=true;
                    valid=false;
                }
                if(this.filters[i].keyWord==''){
                    this.filters[i].invalidKeyword=true;
                    valid=false;
                }
                else if(this.filters[i].filterBy.type===Number && !/\d+/.test(this.filters[i].keyWord)) {
                    this.filters[i].invalidKeyword=true;
                    valid=false;
                }
            }

            return valid;
        },
        sendFilters (){
            var filterFunction = (initialList) => {
                var list = initialList.slice();

                for(var filter of this.filters){
                    for(var i=list.length-1; i>=0; i--){
                        if (list[i][filter.filterBy.category]==null || (filter.filterBy.type === String && list[i][filter.filterBy.category].toLowerCase().indexOf(filter.keyWord.toLowerCase())==-1)){
                            list.splice(i,1);
                        }
                        if (filter.filterBy.type === Number && list[i][filter.filterBy.category] != filter.keyWord) {
                            list.splice(i,1);
                        }
                    }
                }
                
                list = list.sort((a,b) => { return a[this.sortBy] > b[this.sortBy] ? 1 : -1; });

                return list;
            };
            this.$emit('input', filterFunction);
            this.$emit('newSearch');
        }
    }
}
</script>

<style scoped>
    div.filter-option {
        width: 100%;
        text-align: center;
        padding-bottom: 1em;
    }
    select.invalid-category {
        border: 2px solid red;
    }
    input.invalid-keyword {
        border: 2px solid red;
    }
</style>
