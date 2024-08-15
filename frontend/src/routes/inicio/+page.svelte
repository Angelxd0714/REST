<script lang="ts">
	import { onMount } from 'svelte';
	import Card from '../../components/Card.svelte';

	// Declara una variable de estado para almacenar los datos
	let data: { id: number; name: string ; price:number; image:string}[] = [];

	onMount(async () => {
		try {
			const response = await fetch('http://172.27.0.3:8060/api/v1/product/all');
			
			if (!response.ok) {
				console.log(response)
				throw new Error('Network response was not ok');
			}
			const result = await response.json();
			data = result; // Actualiza la variable de estado con los datos
		} catch (error) {
			console.error('Error fetching data:', error);
		}
	});
</script>

<div>
	{#each data as item (item.id)}
		<Card name={item.name} price={item.price} image={item.image}></Card>
	{/each}
	
</div>