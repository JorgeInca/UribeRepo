/* eslint-disable no-nested-ternary */
import { leaftletPoints } from './data';
/*-----------------------------------------------
|   Gooogle Map
-----------------------------------------------*/

const revenueMapInit = () => {
  const themeController = document.body;
  const $googlemaps = document.querySelectorAll('.revenue-map');
  if ($googlemaps.length && window.google) {
    // Visit https://snazzymaps.com/ for more themes
    const mapStyles = {
      SnazzyCustomLight: [
        {
          featureType: 'administrative',
          elementType: 'all',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'administrative',
          elementType: 'labels',
          stylers: [
            {
              visibility: 'on'
            }
          ]
        },
        {
          featureType: 'administrative',
          elementType: 'labels.text.fill',
          stylers: [
            {
              color: '#525b75'
            }
          ]
        },
        {
          featureType: 'administrative',
          elementType: 'labels.text.stroke',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'administrative',
          elementType: 'labels.icon',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'administrative.country',
          elementType: 'geometry.stroke',
          stylers: [
            {
              visibility: 'on'
            },
            {
              color: '#ffffff'
            }
          ]
        },
        {
          featureType: 'administrative.province',
          elementType: 'geometry.stroke',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'landscape',
          elementType: 'geometry',
          stylers: [
            {
              visibility: 'on'
            },
            {
              color: '#E3E6ED'
            }
          ]
        },
        {
          featureType: 'landscape.natural',
          elementType: 'labels',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'poi',
          elementType: 'all',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'road',
          elementType: 'all',
          stylers: [
            {
              color: '#eff2f6'
            }
          ]
        },
        {
          featureType: 'road',
          elementType: 'labels',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'transit',
          elementType: 'labels.icon',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'transit.line',
          elementType: 'geometry',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'transit.line',
          elementType: 'labels.text',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'transit.station.airport',
          elementType: 'geometry',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'transit.station.airport',
          elementType: 'labels',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'water',
          elementType: 'geometry',
          stylers: [
            {
              color: '#F5F7FA'
            }
          ]
        },
        {
          featureType: 'water',
          elementType: 'labels',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        }
      ],
      SnazzyCustomDark: [
        {
          featureType: 'administrative',
          elementType: 'all',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'administrative',
          elementType: 'labels',
          stylers: [{ visibility: 'on' }]
        },
        {
          featureType: 'administrative',
          elementType: 'labels.text.fill',
          stylers: [
            {
              color: '#8a94ad'
            }
          ]
        },
        {
          featureType: 'administrative',
          elementType: 'labels.text.stroke',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'administrative',
          elementType: 'labels.icon',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'administrative.country',
          elementType: 'geometry.stroke',
          stylers: [
            { visibility: 'on' },
            {
              color: '#000000'
            }
          ]
        },
        {
          featureType: 'administrative.province',
          elementType: 'geometry.stroke',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'landscape',
          elementType: 'geometry',
          stylers: [{ visibility: 'on' }, { color: '#222834' }]
        },
        {
          featureType: 'landscape.natural',
          elementType: 'labels',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'poi',
          elementType: 'all',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'road',
          elementType: 'all',
          stylers: [{ color: '#141824' }]
        },
        {
          featureType: 'road',
          elementType: 'labels',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'transit',
          elementType: 'labels.icon',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'transit.line',
          elementType: 'geometry',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'transit.line',
          elementType: 'labels.text',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'transit.station.airport',
          elementType: 'geometry',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'transit.station.airport',
          elementType: 'labels',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'water',
          elementType: 'geometry',
          stylers: [{ color: '#0f111a' }]
        },
        {
          featureType: 'water',
          elementType: 'labels',
          stylers: [{ visibility: 'off' }]
        }
      ]
    };

    $googlemaps.forEach(itm => {
      const mapElement = itm;
      const mapOptions = {
        zoom: 1.4,
        minZoom: 1.4,
        zoomControl: false,
        scrollwheel: true,
        disableDefaultUI: true,
        center: new window.google.maps.LatLng(25.659195, 30.182691),
        // styles: mapStyles.SnazzyCustomLight
        styles:
          window.config.config.phoenixTheme === 'dark'
            ? mapStyles.SnazzyCustomDark
            : mapStyles.SnazzyCustomLight
      };

      const map = new window.google.maps.Map(mapElement, mapOptions);
      const infoWindow = new window.google.maps.InfoWindow();

      const markers = leaftletPoints.map(point => {
        const { name, location, street } = point;

        const label = `
        <h6 class="mb-1">${name}</h6>
        <p class="m-0 text-500">${street}, ${location}</p>
      `;
        const marker = new window.google.maps.Marker({
          position: { lat: point.lat, lng: point.lng }
        });

        marker.addListener('click', () => {
          infoWindow.setContent(label);
          infoWindow.open(map, marker);
        });
        return marker;
      });

      const renderer = {
        render: ({ count, position }) => {
          let color = '#3874ff';
          if (count > 10) {
            color = '#e5780b';
          }
          if (count > 90) {
            color = '#25b003';
          }

          const svg = window.btoa(`
            <svg fill="${color}" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 240 240">
              <circle cx="120" cy="120" opacity=".9" r="70" />
              <circle cx="120" cy="120" opacity=".3" r="90" />
              <circle cx="120" cy="120" opacity=".2" r="110" />
            </svg>`);

          return new window.google.maps.Marker({
            label: { text: String(count), color: 'white', fontSize: '10px' },
            position,
            icon: {
              url: `data:image/svg+xml;base64,${svg}`,
              scaledSize: new window.google.maps.Size(45, 45)
            },
            // adjust zIndex to be above other markers
            zIndex: Number(window.google.maps.Marker.MAX_ZINDEX) + count
          });
        }
      };

      themeController &&
        themeController.addEventListener(
          'clickControl',
          ({ detail: { control, value } }) => {
            if (control === 'phoenixTheme') {
              map.set(
                'styles',
                value === 'dark'
                  ? mapStyles.SnazzyCustomDark
                  : mapStyles.SnazzyCustomLight
              );
            }
          }
        );
      return new window.markerClusterer.MarkerClusterer({
        markers,
        map,
        renderer
      });
    });
  }
};

export default revenueMapInit;
