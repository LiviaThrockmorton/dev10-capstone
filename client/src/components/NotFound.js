import noDuck from "./images/not-found-duck.png";

function NotFound() {
  return (
    <>
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
      <img src={noDuck} alt="Not Found Duck" width="550" height="700"  />
      </div>
    </>
  );
}

export default NotFound;
