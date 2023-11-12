import Carousel from 'react-bootstrap/Carousel';

function Carosel() {
    return (
        <Carousel>
          <Carousel.Item className='h-100'  interval={1000}>
            <img
              className="d-block w-100 h-100"
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSUfTvnq7EFB7IHu7iRpTHNUng3lXLMkprIXM5xpRW6A&s"
              alt="First slide"
            />
            <Carousel.Caption>
              <h3>First slide label</h3>
              <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
            </Carousel.Caption>
          </Carousel.Item>

          <Carousel.Item className='h-100'  interval={500}>
            <img
              className="d-block w-100 h-100"
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfrJZqus2VepKaLCzuoaycUeBH_flej7eG1QY5uew8&s"
              alt="Second slide"
            />
            <Carousel.Caption>
              <h3>Second slide label</h3>
              <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
            </Carousel.Caption>
          </Carousel.Item>

          <Carousel.Item className='h-100' >
            <img
              className="d-block w-100 h-100"
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkHZzPBdpiNSA5vl3x3OFKqfFROc3K4Gj_MtbZMCpP&s"
              alt="Third slide"
            />
            <Carousel.Caption>
              <h3>Third slide label</h3>
              <p>
                Praesent commodo cursus magna, vel scelerisque nisl consectetur.
              </p>
            </Carousel.Caption>
          </Carousel.Item>

        </Carousel>
      );
}

export default Carosel;