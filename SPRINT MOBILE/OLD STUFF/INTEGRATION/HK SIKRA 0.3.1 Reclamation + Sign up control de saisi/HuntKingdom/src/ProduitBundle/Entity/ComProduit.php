<?php

namespace ProduitBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * ComProduit
 *
 * @ORM\Table(name="com_produit")
 * @ORM\Entity(repositoryClass="ProduitBundle\Repository\ComProduitRepository")
 */
class ComProduit
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="contenu", type="string", length=255)
     */
    private $contenu;

    /**
     * @ORM\ManyToOne(targetEntity="Produit")
     * @ORM\JoinColumn(name="idProduit", referencedColumnName="id",onDelete="Cascade")
     */
    private $idProduit;
    /**
     * @ORM\ManyToOne(targetEntity="MainBundle\Entity\User")
     * @ORM\JoinColumn(name="idClient", referencedColumnName="id")
     */
    private $idClient;

    /**
     * Get id.
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set contenu.
     *
     * @param string $contenu
     *
     * @return ComProduit
     */
    public function setContenu($contenu)
    {
        $this->contenu = $contenu;

        return $this;
    }

    /**
     * Get contenu.
     *
     * @return string
     */
    public function getContenu()
    {
        return $this->contenu;
    }

    /**
     * Set idProduit.
     *
     * @param \ProduitBundle\Entity\Produit $idProduit
     *
     * @return ComProduit
     */
    public function setIdProduit(\ProduitBundle\Entity\Produit $idProduit )
    {
        $this->idProduit = $idProduit;

        return $this;
    }

    /**
     * Get idProduit.
     *
     * @return \ProduitBundle\Entity\Produit
     */
    public function getIdProduit()
    {
        return $this->idProduit;
    }

    /**
     * Set idClient.
     *
     * @param \MainBundle\Entity\User $idClient
     *
     * @return ComProduit
     */
    public function setIdClient(\MainBundle\Entity\User $idClient )
    {
        $this->idClient = $idClient;

        return $this;
    }

    /**
     * Get idClient.
     *
     * @return \MainBundle\Entity\User
     */
    public function getIdClient()
    {
        return $this->idClient;
    }
}
