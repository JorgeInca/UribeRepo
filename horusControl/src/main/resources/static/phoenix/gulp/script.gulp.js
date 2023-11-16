/* eslint-disable import/no-extraneous-dependencies */
const gulp = require('gulp');
const babel = require('gulp-babel');
const rollup = require('rollup');
const postcss = require('rollup-plugin-postcss');
const { nodeResolve } = require('@rollup/plugin-node-resolve');
const rollupSourcemaps = require('rollup-plugin-sourcemaps');
const conditional = require('rollup-plugin-conditional');
const uglify = require('@lopatnov/rollup-plugin-uglify');

const {
  paths,
  browserSync: { reload },
  isProd
} = require('./utils');

async function buildPlugin(file) {
  const bundle = await rollup.rollup({
    input: file.path,
    plugins: [
      babel({
        exclude: ['node_modules/**'],
        babelHelpers: 'bundled',
        presets: ['es2015-rollup']
      }),
      postcss({
        config: false
      }),
      nodeResolve(),
      rollupSourcemaps(),
      conditional(isProd, [uglify()])
    ],
    external: ['lodash', 'bootstrap', 'choices.js', 'echarts', 'prismjs'],
    onwarn(warning) {
      if (warning.code === 'THIS_IS_UNDEFINED') {
        return;
      }
      console.warn(warning.message);
    }
  });
  await bundle
    .write({
      output: {
        dir: isProd ? 'build/assets/js' : 'public/assets/js',
        entryFileNames: '[name].js',
        format: 'umd',
        name: file.name,
        sourcemap: true,
        globals: {
          bootstrap: 'bootstrap',
          'choices.js': 'Choices'
        }
      }
    })
    .then(() => reload());
}

const listOfFile = async () => {
  await paths.script.bundles.map(async file => {
    await buildPlugin(file);
  });
};

gulp.task('script', gulp.series(listOfFile));
