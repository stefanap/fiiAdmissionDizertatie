import { field } from "form-for";
import { bindBootstrapFieldComponents } from "form-for-bootstrap-components";

export default class User {
  @field name;
  @field surname;

  @field({ type: "tel" })
  phone;

  @field({ type: "email" })
  email;

  @field({ type: "select", required: true, options: ['guest', 'admin'] })
  access;

  bindBootstrapFieldComponents();
}

