import facade from "../facades/sportFacade";
import React, { useState, useEffect } from "react";
import { Jumbotron, Row, Col, Form, Table } from "react-bootstrap";
import { sportURL as url } from "../utils/settings";
import { handleHttpErrors, makeOptions } from "../utils/fetchUtils";

export default function Sports({ sports, setSports }) {
  useEffect(() => {
    facade.fetchSports().then((data) => {
      setSports([...data.all]);
    });
  }, []);

  return (
    <Row>
      <Col></Col>
      <Col>
        <h1>Sports</h1>
        <Table striped bordered hover className="m-5">
          <thead>
            <tr>
              <td>
                <h4>Name</h4>
              </td>
              <td>
                <h4>Description</h4>
              </td>
            </tr>
          </thead>
          <tbody>
            {sports.map((p) => {
              return (
                <tr key={p.id}>
                  <td>{p.name}</td>
                  <td>{p.description}</td>
                </tr>
              );
            })}
          </tbody>
        </Table>
      </Col>
      <Col></Col>
    </Row>
  );
}
