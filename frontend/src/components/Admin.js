import facade from "../facades/sportFacade";
import React, { useState, useEffect } from "react";
import { Jumbotron, Row, Col, Form, Button } from "react-bootstrap";
import { makeOptions, handleHttpErrors } from "../utils/fetchUtils";
import { sportURL as url } from "../utils/settings";

export default function Admin() {
  const init = {
    name: "",
    description: "",
  };
  const [sport, setSport] = useState({ ...init });

  const onChange = (evt) => {
    setSport({
      ...sport,
      [evt.target.id]: evt.target.value,
    });
    console.log({ ...init });
  };
  const onClick = (evt) => {
    evt.preventDefault();
    const options = makeOptions("POST", true, sport);
    fetch(url, options)
      .then(handleHttpErrors)
      .then((data) => {
        console.log({ ...sport });
        setSport({ ...sport });
      })
      .catch((err) => {
        if (err.status) {
          err.fullError.then((e) => alert(e.message));
        } else {
          console.log("Network error");
        }
      });
  };

  return (
    <>
      <Col></Col>
      <Col>
        <h1>Add new sport to the club</h1>
        <Form.Group className="m-5">
          <Form.Label for="name">Sport name:</Form.Label>
          <br />
          <Form.Control type="text" id="name" onChange={onChange} />
          <br />
          <Form.Label for="description">Sport description:</Form.Label>
          <br />
          <Form.Control type="text" id="description" onChange={onChange} />
          <br />

          <Button type="button" onClick={onClick}>
            Add Sport
          </Button>
        </Form.Group>
      </Col>
      <Col></Col>
    </>
  );
}
