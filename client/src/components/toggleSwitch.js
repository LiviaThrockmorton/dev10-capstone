import React, { useState } from "react";

const ToggleSwitch = ({ outfitId, posted, handleChange }) => {
  const [isChecked, setIsChecked] = useState(posted);

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
    </div>
  );
};

export default ToggleSwitch;
