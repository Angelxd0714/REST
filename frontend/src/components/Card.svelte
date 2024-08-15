<script lang="ts">
    import { Card, Button, Toggle } from 'flowbite-svelte';

    export let name:string = "";
    export let image:string = ""
    export let price:number = 0;
   
</script>
  
<div  class="space-y-4 mt-4 ml-2">
    <Card img={image}>
      <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">{name}</h5>
      <p class="mb-3 font-normal text-gray-700 dark:text-gray-400 leading-tight">{price}</p>
      <Button>
        Read more
      </Button>
    </Card>
  </div>