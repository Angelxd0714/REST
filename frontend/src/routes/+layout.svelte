<script>
	import '../app.css';
	import { page } from '$app/stores';
	import { Navbar, NavBrand, NavLi, NavUl, NavHamburger } from 'flowbite-svelte';
	$: activeUrl = $page.url.pathname;
</script>

<header>
	<Navbar>
		<NavHamburger />
		<NavUl {activeUrl}>
			<NavLi href="/inicio">Inicio</NavLi>
			<NavLi href="/addProduct">Agregar Producto</NavLi>
			<NavLi href="/markers">Fabricantes</NavLi>
		</NavUl>
	</Navbar>
</header>
<main class="">
	<slot></slot>
</main>
<footer></footer>
