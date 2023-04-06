import sadDuck from "./images/sad-duck.svg";

function NotFound() {
  return (
    <>
    
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
      <img src={sadDuck} alt="Not Found" width="60%" />
      </div>
    </>
  );
}

export default NotFound;
