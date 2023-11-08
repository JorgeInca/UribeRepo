import flatpickr from 'flatpickr';
// import {l10n} from

/* -------------------------------------------------------------------------- */
/*                                  Flatpickr                                 */
/* -------------------------------------------------------------------------- */

const flatpickrInit = () => {
  const { getData } = window.phoenix.utils;
  document.querySelectorAll('.datetimepicker').forEach(item => {
    const userOptions = getData(item, 'options');
    const datepicker = flatpickr(item, {
      nextArrow: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512"><!--! Font Awesome Pro 6.1.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. --><path d="M96 480c-8.188 0-16.38-3.125-22.62-9.375c-12.5-12.5-12.5-32.75 0-45.25L242.8 256L73.38 86.63c-12.5-12.5-12.5-32.75 0-45.25s32.75-12.5 45.25 0l192 192c12.5 12.5 12.5 32.75 0 45.25l-192 192C112.4 476.9 104.2 480 96 480z"/></svg>`,
      prevArrow: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512"><!--! Font Awesome Pro 6.1.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. --><path d="M224 480c-8.188 0-16.38-3.125-22.62-9.375l-192-192c-12.5-12.5-12.5-32.75 0-45.25l192-192c12.5-12.5 32.75-12.5 45.25 0s12.5 32.75 0 45.25L77.25 256l169.4 169.4c12.5 12.5 12.5 32.75 0 45.25C240.4 476.9 232.2 480 224 480z"/></svg>`,
      locale: {
        firstDayOfWeek: 0,

        shorthand: ['S', 'M', 'T', 'W', 'T', 'F', 'S']
      },
      monthSelectorType: 'static',
      onDayCreate: (dObj, dStr, fp, dayElem) => {
        if (dayElem.dateObj.getDay() === 5 || dayElem.dateObj.getDay() === 6) {
          dayElem.className += ' weekend-days';
        }
      },
      ...userOptions
    });

    // datepicker.l10n.weekdays.shorthand = ['S', 'M', 'T', 'W', 'T', 'F', 'S'];
  });
};

export default flatpickrInit;
