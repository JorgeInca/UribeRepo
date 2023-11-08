/* -------------------------------------------------------------------------- */
/*                               Ratings                               */
/* -------------------------------------------------------------------------- */

const ratingInit = () => {
  const { getData, getItemFromStore } = window.phoenix.utils;
  const raters = document.querySelectorAll('[data-rater]');

  raters.forEach(rater => {
    const options = {
      reverse: getItemFromStore('phoenixIsRTL'),
      starSize: 32,
      step: 0.5,
      element: rater,
      rateCallback(rating, done) {
        this.setRating(rating);
        done();
      },
      ...getData(rater, 'rater')
    };

    return window.raterJs(options);
  });
};
export default ratingInit;
