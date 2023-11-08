const gulp = require('gulp');
const sharpResponsive = require('gulp-sharp-responsive');

gulp.task('image', async () => {
  gulp
    .src('public/assets/img/sections/**/*.{jpg,png}')
    .pipe(
      sharpResponsive({
        formats: [
          {
            // width: 640,
            format: 'webp',
            width: metadata => metadata.width
          }
        ]
      })
    )
    .pipe(gulp.dest('dist/img'));
});
