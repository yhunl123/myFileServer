import { sveltekit } from '@sveltejs/kit/vite';
import { defineConfig } from 'vite';
import commonjs from "rollup-plugin-commonjs";

export default defineConfig({
	plugins: [sveltekit()],
	// ... other config
	rollupInputOptions: {
		plugins: [commonjs()]
	},
	server: {
		proxy: {
			'/api/': {
				target:'http://localhost:8089/api/',
				pathRewrite: {'^/api/': ''},
				rewrite: (path) => path.replace('/api/', ''),
				changeOrigin: true
			}
		},
	}
});
