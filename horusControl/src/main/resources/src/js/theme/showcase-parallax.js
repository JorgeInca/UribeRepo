import { gsap } from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';

gsap.registerPlugin(ScrollTrigger);
/* -------------------------------------------------------------------------- */
/*                                  Count Up                                  */
/* -------------------------------------------------------------------------- */
const { merge } = window._;
const showcaseParallaxInit = () => {
  const { getData } = window.phoenix.utils;
  const elements = document.querySelectorAll('[data-gsap]');
  const parallaxElements = document.querySelectorAll('[data-gsap-parallax]');

  if (parallaxElements) {
    Array.from(parallaxElements).forEach(el => {
      const elOptions = getData(el, 'gsap-parallax');

      const options = merge(
        {
          // y: 100,

          ease: 'none',
          scrollTrigger: {
            trigger: el,
            scrub: true,
            start: 'top bottom',
            toggleActions: 'play none none reverse'
          }
        },
        elOptions
      );
      gsap.to(el, options);
    });
  }

  if (elements) {
    Array.from(elements).forEach(el => {
      const val = getData(el, 'gsap');
      gsap.to(el, {
        y: val,
        ease: 'none',
        scrollTrigger: {
          trigger: '.gsap',
          scrub: true,
          start: '+=450 bottom',
          toggleActions: 'play none none reverse'
        }
      });
    });
  }

  gsap.to('.feature-figma-img', {
    y: '-50%',
    scrollTrigger: {
      trigger: '.feature-figma-img',
      toggleActions: 'play none none reverse',
      scrub: true,
      start: 'top bottom'
      // end: 'top top'
    }
  });

  gsap.to('.bg-gradient-figma', {
    y: '-90%',
    scrollTrigger: {
      trigger: '.bg-gradient-figma',
      toggleActions: 'play none none reverse',
      scrub: true,
      start: 'top bottom',
      end: 'top -20%'
    }
  });
};

export default showcaseParallaxInit;
