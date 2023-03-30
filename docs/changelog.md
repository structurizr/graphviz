# Changelog

## 2.0.0 (unreleased)

- Switched to using the [https://github.com/structurizr/export](structurizr-export library) for generating DOT files. 

## 1.8.1 (13th March 2023)

- Fixes warning about clusters with the same name.
- Fixes an issue when nested groups are configured, but groups are not nested.

## 1.8.0 (12th March 2023)

- Adds support for nested groups.

## 1.7.0 (16th February 2023)

- Updated to reflect breaking changes in `structurizr-core`.

## 1.6.1 (1st October 2021)

- Adds support for custom elements on deployment views.

## 1.6.0 (3rd September 2021)

- Adds support for custom page sizes.
- Adds support for custom views.
- Takes layout settings from the view when specified, otherwise defaults are used.

## 1.5.0 (28th March 2021)

- Adds support for relationships between deployment nodes.
- Adds support for element grouping.
- Fixes a bug with double-quote characters in element names.

## 1.4.0 (18th December 2020)

- Adds support for return/response messages on dynamic views.
- Adds support for software system instances on deployment views.
- Fixes a bug where a view's paper size isn't set when the diagram is larger than A0.

## 1.3.7 (27th June 2020)

- Fixes a bug that assumed some numbers in the SVG output from Graphviz would be integers.

## 1.3.6 (9th June 2020)

- Fixes issues with running the Graphviz extension in non-English locales.

## 1.3.5 (8th April 2020)

- Initial version.