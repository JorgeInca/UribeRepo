/* -------------------------------------------------------------------------- */
/*                               Ratings                               */
/* -------------------------------------------------------------------------- */

const randomColorInit = () => {
  const { getData } = window.phoenix.utils;

  const randomColorElements = document.querySelectorAll('[data-random-color]');
  const defaultColors = [
    '#EFF2F6',
    '#E3E6ED',
    '#CBD0DD',
    '#85A9FF',
    '#60C6FF',
    '#90D67F',
    '#F48270',
    '#FFCC85',
    '#3874FF',
    '#0097EB',
    '#25B003',
    '#EC1F00',
    '#E5780B',
    '#004DFF',
    '#0080C7',
    '#23890B',
    '#CC1B00',
    '#D6700A',
    '#222834',
    '#3E465B',
    '#6E7891',
    '#9FA6BC'
  ];

  randomColorElements.forEach(el => {
    const userColors = getData(el, 'random-color');
    let colors;
    if (Array.isArray(userColors)) {
      colors = [...defaultColors, ...userColors];
    } else {
      colors = [...defaultColors];
    }

    el.addEventListener('click', e => {
      const randomColor =
        colors[Math.floor(Math.random() * (colors.length - 1))];
      e.target.value = randomColor;
      const inputLabel = e.target.nextElementSibling;
      // e.target.nextElementSibling.style.boxShadow = `0 0 0 0.2rem ${randomColor}`;
      inputLabel.style.background = `${randomColor}`;
      inputLabel.style.borderColor = `${randomColor}`;
      inputLabel.style.color = `white`;
    });
  });
};
export default randomColorInit;
