import React, { useState } from "react";

const ToggleSwitch = ({ outfitId, hidden, handleChange }) => {
  const [isChecked, setIsChecked] = useState(hidden);

  const onChange = () => {
    handleChange(!isChecked);
    setIsChecked(prevState => !prevState);
  };

  return (
    <div>
      <label className="switch">
        <input type="checkbox" checked={isChecked} onChange={onChange} />
        <span className="slider round">
          <span className="switch-label">Posted</span>
          {isChecked && (
            <span className="switch-label-right">Private</span>
          )}
        </span>
      </label>
      {/* {!isChecked && (
        <img src= {duck.duckImage} alt="Duck" />
      )} */}
    </div>
  );
};

export default ToggleSwitch;
