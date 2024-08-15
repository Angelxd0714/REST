<script>
	import '../app.css';
</script>

<header>
	<nav class="bg-transparent items-center w-full flex flex-row justify-center h-20 text-black">
		<ul class="flex flex-row gap-4">
			<li><a href="/">Home</a></li>
			<li><a href="/about">About</a></li>
		</ul>
	</nav>
</header>
<main>
	<slot></slot>
</main>
