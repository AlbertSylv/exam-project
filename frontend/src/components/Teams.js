import facade from "../facades/sportFacade";
import React, { useState, useEffect } from "react";
import { Jumbotron, Row, Col, Form, Table } from "react-bootstrap";

export default function Teams({ sportTeams, setSportTeams }) {
  useEffect(() => {
    facade.fetchSportTeams().then((data) => {
      setSportTeams([...data.all]);
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
                <h4>Team name</h4>
              </td>
              <td>
                <h4>Minimum age</h4>
              </td>
              <td>
                <h4>Maximum age</h4>
              </td>
              <td>
                <h4>Price per year</h4>
              </td>
              <td>
                <h4>Sport</h4>
              </td>
            </tr>
          </thead>
          <tbody>
            {sportTeams.map((p) => {
              return (
                <tr key={p.id}>
                  <td>{p.teamName}</td>
                  <td>{p.minAge}</td>
                  <td>{p.maxAge}</td>
                  <td>{p.pricePerYear}</td>
                  <td>{p.sport.name}</td>
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
