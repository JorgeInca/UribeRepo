/*-----------------------------------------------
|                     Isotope
-----------------------------------------------*/

const isotopeInit = () => {
  const { getData } = window.phoenix.utils;
  const Selector = {
    ISOTOPE_ITEM: '.isotope-item',
    DATA_ISOTOPE: '[data-sl-isotope]',
    DATA_FILTER: '[data-filter]',
    DATA_FILER_NAV: '[data-filter-nav]'
  };

  const DATA_KEY = {
    ISOTOPE: 'sl-isotope'
  };
  const ClassName = {
    ACTIVE: 'active'
  };

  if (window.Isotope) {
    const masonryItems = document.querySelectorAll(Selector.DATA_ISOTOPE);
    masonryItems.length &&
      masonryItems.forEach(masonryItem => {
        window.imagesLoaded(masonryItem, () => {
          masonryItem.querySelectorAll(Selector.ISOTOPE_ITEM).forEach(item => {
            // eslint-disable-next-line
            item.style.visibility = 'visible';
          });

          const userOptions = getData(masonryItem, DATA_KEY.ISOTOPE);
          const defaultOptions = {
            itemSelector: Selector.ISOTOPE_ITEM,
            layoutMode: 'packery'
          };

          const options = window._.merge(defaultOptions, userOptions);
          const isotope = new window.Isotope(masonryItem, options);

          // --------- filter -----------------
          const filterElement = document.querySelector(Selector.DATA_FILER_NAV);
          filterElement?.addEventListener('click', function (e) {
            const item = e.target.dataset.filter;
            isotope.arrange({ filter: item });
            document.querySelectorAll(Selector.DATA_FILTER).forEach(el => {
              el.classList.remove(ClassName.ACTIVE);
            });
            e.target.classList.add(ClassName.ACTIVE);
          });
          // ---------- filter end ------------

          return isotope;
        });
      });
  }
};

export default isotopeInit;
